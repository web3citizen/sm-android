�}A  �  ���ב���e��S��u6��*�LIX7�S���ȯ�L�J��V�`�D��{b n���瀃N�,E]�i�4��s�Χ�S��h!���8�;�=���D�!Q���_([ ��:�K����j$o��'B�ytTm�_%͟��c�����30(z���o���y�YA��@���uJ�ʽ�A�XZ☹1��_Xfj������o����u`��P��w�d��G�s�c��M�t�<uJ����?��t���[t�!g@Ͳ��,?��C��B"�Iٵɔ`�;���y܍�,P޺�}C%O]�Y��0��
5u	񘾋F@m�
�Ax�\�K�5f����2K�@\��OD�����ÂQ؍��C�����Ϋ��5�;�h"�3�AjT{*�r��¬FPڳs���\�ʴ`�V/,�2_I��y���� �mX����������>�i�f@�w��DZ���g���4�� B�%�OY;i4O�:��/�/�lD/d��թ����@J�Ce��l��C(��`A�:�p�Ñ#Ub��:N̴H��d{K�CE+CDD��w5݈b�_��}�-$e�aY��]C������,5z@����(GQ3Ix����)��Nuya���6�&2Ҥq䞒]D���}�*���^�$����)�G�,��*>�pa�C]��W�@-��>��^�O(L'�j(	���úk׽���`7�&(�gkU/AV��g��9OJ+�I����d�X����I����$�����gBt�#�������y%� ���u����V��&F��7�"۪~Q��І�;R�ݷ�Sz�t�IH��<�$���,�ճ�ῇ_��_���T�qmȵE�����%�� 4C�����I���Γ��X�����􊛏
�a�,Y���R��w�j�z�w�"{��E-Gǘ��{&(��\�Y�"��*۠���DV�M>-�V�$�����Q��<�A1���?Ɨ�"_�_ں�r{/�������?l�&^�/E�FXڌ����"Y� G�6�&����˝�B���U��u�j���9�hl8+གྷ�.���p�TX� �d��5/��Æ�J8�l�*Fj�geŒbf� ��`���a��v����2Fv¸5�_/�8�t��QUˆʝ�Z�̄�+�C��[��'�"'}5$��
��)IJn����U��n��A9��<��xF�:q�E����|��W^�{��plVUQ�{�S)e�_^Ev��S�\X�^�����g�v�=?�X��R�l�Z R���F��	ݾ���H�PP�]���Du�xt� �]k7b/>$���D�!��j�.��?gKu^W|Qi��3�	>����q�5�5ރZ��3FڰmF��;[oR�\^qDJC��lA��܈8�cFDv����M)e��F���s���aH-��ǡ��'c��>h	��B�Ļ$�-����߹���RVM�B�>�g����0� ��a�0�+2����m��Ń ��ԡ����{���cyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

    public interface ItemTouchHelperAdapter {

        void onItemDismiss(int position);
    }
}
