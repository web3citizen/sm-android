�}�A  �r  ��l�\���e��S��u6��*�LIX7�S���ȯ�L�J��V�`�D��{b n���瀃N�,E]�i�4��s�Χ�S��h!���8�;�=���D�!Q���_([ ��:�K����j$o��'B�ytTm�_%͟��c�����30(z���o���y�YA��@���uJ�ʽ�A�XZ☹1��_Xfj������o����u`��P��w�d��G�s�c��M�t�<uJ����?��t���[t�!g@Ͳ��,?��C��B"�Iٵɔ`�;���y܍�,P޺�}C%O]�Y��0��
5u	񘾋F@m�
�Ax�\�K�5f����2K�@\��OD�����ÂQ؍��C�����Ϋ��5�;�h"�3�AjT{*�r��¬FPڳs���\�ʴ`�V/,�2_I��y���� �mX����������>�i�f@�w��DZ���g���4�� B�%�OY;i4O�:��/�/�l�*���i�2�v�4� P���Q2Q�P���f6�!�ʍ�ГA����nD4X(����g�-��m�P�g�3�5�իj1E�Ձ�������8��!r��~�������6y��D���BV�KZpbm��eQ�����>�C����i��WS��aF���O��A�za�J�!*��O�o2K !$~�W�!�Z/�wFe���@��L�<45�����E��A�&��"cox%v���:#T�U�wo�w[&��%o�+p��O"(-�;�n�m�~c��u6�g�]"^���}I���C� 	&{<+G,6��O��!�@�V�1͛�*��XQ�n�v�B}ߑ0���[�b{�NL��d�+���?4#�%'�k��Q(n���nn��r�3�r��i
)��)^z�"	�bQ5�ï�ȩŵ[ZH�8 �z������������[U���>8��ʢ+hD��|����@�0 ���S��R����o��I.W�wD�A�6�`$ݯ
    public static void saveTag(Tag tag) {
        tag.save();
    }

    public static List<Tag> getAllTags() {
        return new Select().from(Tag.class).queryList();
    }


    public static void saveRepo(Repo repo) {
        repo.save();
    }

    public static List<Repo> queryAllRepo() {
        return new Select().from(Repo.class).queryList();
    }

    public static List<Repo> queryReposByTag(Tag tag) {
        return new Select().from(Repo.class).queryList();
    }
}
